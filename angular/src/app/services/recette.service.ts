import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Recette} from '../model/recette';
import {Difficulte} from '../model/difficulte.enum';
import {Couts} from '../model/couts.enum';

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
      console.log(res);
    });

    return null;
  }
  public findByNomDiffAndCout(nom?: string, diff?: Difficulte, cout?: Couts, nodiff?: Difficulte, nocout?: Couts): Observable<Array<Recette>>{
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
