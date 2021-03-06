import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {
  forkJoin,
  ObjectUnsubscribedError,
  Observable,
  Subscription,
} from 'rxjs';
import { Recette } from '../model/recette';
import { debounceTime, map, mergeMap } from 'rxjs/operators';
import { Difficulte } from '../model/difficulte.enum';
import { Cout } from '../model/cout.enum';
import { Ingredient } from '../model/ingredient';
import { ImageModel } from 'src/app/model/image-model';

@Injectable({
  providedIn: 'root',
})
export class RecetteService {
  private URL: string = 'http://localhost:8080/web/rest';
  private headers: HttpHeaders;
  private options: object;

  constructor(private httpClient: HttpClient) {}

  public checkNom(nom: string): Observable<any> {
    return this.httpClient.get(`${this.URL}/recette/nom/${nom}`);
  }

  public findByAuteur(auteur: string): Observable<Array<Recette>> {
    return this.httpClient.get<Array<Recette>>(
      `${this.URL}/recette/auteur/${auteur}`
    );
  }

  public findBest(): Observable<Array<Recette>> {
    let note: number = 5;
    let params = new HttpParams().set('note', note.toString());
    return this.httpClient.get<Array<Recette>>(`${this.URL}/recette/search`, {
      params: params,
    });
  }
  public findAll(): Observable<Array<Recette>> {
    return this.httpClient.get<Array<Recette>>(`${this.URL}/recette/all`);
  }

  public findById(id: number): Observable<Recette> {
    return this.httpClient.get<Recette>(`${this.URL}/recette/all/id/${id}`);
  }

  public create(recette: Recette, img: any): any {
    let id: number;
    const uploadData = new FormData();

    uploadData.append('image', img, img.name);

    return this.httpClient
      .post('http://localhost:8080/web/rest/image/upload', uploadData)
      .pipe(
        mergeMap((image) => {
          console.log(image);
          let imgNew = image as ImageModel;
          // prettier-ignore
          const o: object = {
              'nom': recette.nom,
              'type': recette.type,
              'nbPersonne': recette.nbPersonne,
              'temps': recette.temps,
              'difficulte': recette.difficulte,
              'cout': recette.cout,
              'dateCreation': recette.dateCreation,
              'picture': {
                'id': imgNew.id
              }
            };
          let parametres = new HttpParams();
          parametres = parametres.append(
            'auteur',
            sessionStorage.getItem('login')
          );
          let recetteNew: Recette = new Recette();
          let type: string = recette.type.toString().toLowerCase();

          return this.httpClient
            .post(`${this.URL}/${type}`, o, { params: parametres })
            .pipe(
              mergeMap((res) => {
                recetteNew = res as Recette;
                id = recetteNew.id;

                let etapes = recette.etapes;
                let objetEtapes = [];
                for (let etape of etapes) {
                  objetEtapes.push({
                    texte: etape.texte,
                    numEtape: etape.numEtape,
                    id_recette: {
                      id: id,
                      type: recette.type,
                    },
                  });
                }
                let createEtapes = this.httpClient.post(
                  `${this.URL}/etape`,
                  objetEtapes
                );
                let tableau: any[] = [];
                tableau.push(createEtapes);

                for (let ing of recette.ingredients) {
                  let objetIng: object = {
                    nom: ing.nom,
                  };
                  let parametres = new HttpParams();
                  parametres = parametres.append('idr', id.toString());
                  parametres = parametres.append(
                    'quantite',
                    ing.quantite.toString()
                  );
                  parametres = parametres.append('unite', ing.unite);

                  let createIngredient = this.httpClient.post(
                    `${this.URL}/ingredient/assoc`,
                    objetIng,
                    { params: parametres }
                  );
                  tableau.push(createIngredient);
                }

                return forkJoin(tableau);
              })
            );
        })
      );
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete(`${this.URL}/recette/id/${id}`);
  }

  public findByNomDiffAndCout(
    nom?: string,
    diff?: Difficulte,
    cout?: Cout,
    nodiff?: Difficulte,
    nocout?: Cout
  ): Observable<Array<Recette>> {
    const params = new HttpParams();
    params.append('namelike', nom);
    params.append('diff', diff);
    params.append('cout', cout);
    params.append('nodiff', nodiff);
    params.append('nocout', nocout);
    console.log(`${this.URL}/search`, { params: params });
    return this.httpClient.get<Array<Recette>>(`${this.URL}/search`, {
      params: params,
    });
  }
}
