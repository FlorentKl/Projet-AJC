import { Injectable } from '@angular/core';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Commentaire } from 'src/app/model/commentaire';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommentaireService {
  private URL: string = 'http://localhost:8080/web/rest/commentaire';
  constructor(private httpClient: HttpClient) {}

  public finAllFromRecette(id: number): Observable<Array<Commentaire>> {
    return this.httpClient.get<Array<Commentaire>>(`${this.URL}/${id}`);
  }
  public create(
    commAuteur: string,
    idRecette: number,
    commentaire: Commentaire
  ): Observable<any> {
    let params = new HttpParams()
      .set('auteur', commAuteur)
      .set('id_recette', idRecette.toString());

    let comm: object = {
      note: commentaire.note,
      texte: commentaire.texte,
    };

    return this.httpClient.post(this.URL, comm, { params: params });
  }
}
