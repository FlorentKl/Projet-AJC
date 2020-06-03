import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {forkJoin, ObjectUnsubscribedError, Observable, Subscription} from 'rxjs';
import {Recette} from '../model/recette';
import {debounceTime, map, mergeMap} from 'rxjs/operators';
import {Difficulte} from '../model/difficulte.enum';
import {Cout} from '../model/cout.enum';


@Injectable({
  providedIn: 'root'
})
export class RecetteService {

  private URL: string = 'http://localhost:8080/web/rest';
  private headers: HttpHeaders;
  private options: object;

  constructor(private httpClient: HttpClient) {
  }

  public findAll(): Observable<Array<Recette>> {
    console.log('hello');
    console.log(this.httpClient.get<Array<Recette>>(this.URL));
    return this.httpClient.get<Array<Recette>>(`${this.URL}/recette/all`);
  }

  public findById(id: number): Observable<Recette> {
    return this.httpClient.get<Recette>(`${this.URL}/recette/all/id/${id}`);
  }

  public create(recette: Recette): Observable<any> {
    let id: number;
    const o: object = {
      'nom': recette.nom,
      'type': recette.type,
      'nbPersonne': recette.nbPersonne,
      'temps': recette.temps,
      'difficulte': recette.difficulte,
      'cout': recette.cout
    };

    let recetteNew: Recette = new Recette();
    let type: string = recette.type.toString().toLowerCase();
    return this.httpClient.post(`${this.URL}/${type}`, o).pipe(mergeMap(res => {






      recetteNew = res as Recette;
      id = recetteNew.id;
      console.log('PARTIE create Etapes');

      let etapes = recette.etapes;
      let objetEtapes = [];
      for (let etape of etapes) {
        objetEtapes.push(
          {
            'texte': etape.texte,
            'numEtape': etape.numEtape,
            'id_recette': {
              'id': id,
              'type': recette.type
            }
          });
      }
      let createEtapes = this.httpClient.post(`${this.URL}/etape`, objetEtapes);


      for (let ing of recette.ingredients) {
        console.log(ing.nom + " / " + ing.quantite + " / " + ing.unite);
        let parametres = new HttpParams();
        parametres = parametres.append('idr', id.toString());
        parametres = parametres.append('nom', ing.nom);
        parametres = parametres.append('quantite', ing.quantite.toString());
        parametres = parametres.append('unite', ing.unite);
        this.httpClient.post(`${this.URL}/ingredient/assoc`, {params: parametres});
      }



      return forkJoin([createEtapes]);

      // let createIngredients = this.httpClient.post(url, OBJET);
      // return forkJoin([createEtapes, createIngredients]);

      })
    );
  }



  public createa(recette: Recette): Observable<any> {
    let id: number;
    const o: object = {
      'nom': recette.nom,
      'type': recette.type,
      'nbPersonne': recette.nbPersonne,
      'temps': recette.temps,
      'difficulte': recette.difficulte,
      'cout': recette.cout
    };

    let recetteNew;
    let type: string = recette.type.toString().toLowerCase();
    this.httpClient.post(`${this.URL}/${type}`, o).subscribe(res => {
      recetteNew = res as Recette;
      id = recetteNew.id;
      console.log("1");
      console.log(recetteNew);
    });

    console.log("2");
    console.log(recetteNew);

    let etapes = recette.etapes;
    let objetEtapes = [];
    for (let etape of etapes) {
      objetEtapes.push(
        {
          'texte': etape.texte,
          'numEtape': etape.numEtape,
          'id_recette': recetteNew
        });
    }
    return this.httpClient.post(`${this.URL}/etape`, objetEtapes);
  }
  public findByNomDiffAndCout(nom?: string, diff?: Difficulte, cout?: Cout, nodiff?: Difficulte, nocout?: Cout): Observable<Array<Recette>>{
    const params = new HttpParams();
    params.append('namelike', nom );
    params.append('diff', diff);
    params.append('cout', cout);
    params.append('nodiff', nodiff);
    params.append('nocout', nocout);
    console.log(`${this.URL}/search`, {params: params});
    return this.httpClient.get<Array<Recette>>(`${this.URL}/search`, {params: params});
  }

}
