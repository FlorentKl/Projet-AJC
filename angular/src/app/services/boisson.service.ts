import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Recette} from '../model/recette';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BoissonService {
  private URL: string = 'http://localhost:8080/web/rest/boisson';
  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Array<Recette>>{
    return this.httpClient.get<Array<Recette>>(this.URL + '/all');
  }

  public findById(id: number): Observable<Recette>{
    return this.httpClient.get<Recette>(`${this.URL}/${id}`);
  }
}
