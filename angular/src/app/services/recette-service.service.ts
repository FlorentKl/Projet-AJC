import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Recette} from '../model/recette';

@Injectable({
  providedIn: 'root'
})
export class RecetteServiceService {
  private URL: string = 'http://localhost:8080/web/rest/recette';


  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Array<Recette>>{
    return this.httpClient.get<Array<Recette>>(this.URL + '/all');
  }
  public findById(id: number): Observable<Recette>{
    return this.httpClient.get<Recette>(`${this.URL}/${id}`);
  }
}
