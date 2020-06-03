import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {forkJoin, ObjectUnsubscribedError, Observable, Subscription} from 'rxjs';
import {Recette} from '../model/recette';
import {debounceTime, map, mergeMap} from 'rxjs/operators';

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


}
