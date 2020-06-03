import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from 'src/app/user/model/utilisateur';

@Injectable({
  providedIn: 'root',
})
export class InscriptionService {
  private URL: string = 'http://localhost:8080/web/rest/inscription';

  constructor(private http: HttpClient) {}

  public inscription(user: Utilisateur): Observable<any> {
    const o = {
      pseudo: user.username,
      password: user.password,
    };
    return this.http.post(this.URL, o);
  }

  public available(login: string): Observable<boolean> {
    return this.http.get<boolean>(this.URL + login);
  }
}
