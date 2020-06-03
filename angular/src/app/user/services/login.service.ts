import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Utilisateur } from 'src/app/user/model/utilisateur';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private URL: string = 'http://localhost:8080/web/rest/login/';
  constructor(private httpClient: HttpClient) {}

  public login(user: Utilisateur): Observable<any> {
    // prettier-ignore
    const headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(`${user.username}:${user.password}`),
    });

    return this.httpClient.get(this.URL, {
      headers: headers,
    });
  }
}
