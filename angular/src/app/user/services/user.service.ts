import { Injectable } from '@angular/core';
import { Utilisateur } from 'src/app/user/model/utilisateur';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private URL: string = 'http://localhost:8080/web/rest/inscription';

  constructor(private httpClient: HttpClient) {}

  public create(user: Utilisateur) {
    const o: object = {
      username: user.username,
      password: user.password,
    };
    return this.httpClient.post(this.URL, o);
  }

  public checkUsername(username: string): Observable<any> {
    return this.httpClient.get(`${this.URL}/check/${username}`);
  }
}
