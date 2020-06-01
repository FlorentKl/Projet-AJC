import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Recette} from '../model/recette';

@Injectable({
  providedIn: 'root'
})
export class PlatService {
  private URL: string = 'http://localhost:8080/web/rest/plat';


  constructor(private httpClient: HttpClient) {
  }

  public findAll(): Observable<Array<Recette>> {
    return this.httpClient.get<Array<Recette>>(this.URL + '/all');
  }

  public findById(id: number): Observable<Recette> {
    return this.httpClient.get<Recette>(`${this.URL}/${id}`);
  }
}
