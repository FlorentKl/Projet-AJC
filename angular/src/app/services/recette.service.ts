import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Recette} from '../model/recette';

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
    let type: string = recette.type.toString().toLowerCase();
    console.log(o);
    this.httpClient.post(`${this.URL}/${type}`, o).subscribe(res => {
      id = res.id;
    });

    //gestion des étapes



    return null;
  }


}
