import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Utilisateur } from 'src/app/user/model/utilisateur';
import { LoginService } from 'src/app/user/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private _user: Utilisateur = new Utilisateur();
  private _erreur: boolean = false;

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {}

  public send() {
    this.loginService.login(this.user).subscribe(
      (res) => {
        sessionStorage.setItem(
          'user',
          btoa(`${this.user.username}:${this.user.password}`)
        );
        sessionStorage.setItem('login', this.user.username);
        this.router.navigate(['/index']);
      },
      (err) => {
        this.erreur = true;
      }
    );
  }

  public get user(): Utilisateur {
    return this._user;
  }

  public set user(v: Utilisateur) {
    this._user = v;
  }

  public get erreur(): boolean {
    return this._erreur;
  }

  public set erreur(value: boolean) {
    this._erreur = value;
  }
}
