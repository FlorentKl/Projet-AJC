import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user/services/user.service';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { map } from 'rxjs/operators';
import { Utilisateur } from 'src/app/user/model/utilisateur';
import { Router } from '@angular/router';
import { InscriptionService } from 'src/app/user/services/inscription.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
})
export class InscriptionComponent implements OnInit {
  show1: boolean;
  show2: boolean;

  private _user: Utilisateur = new Utilisateur();
  private _loginCtrl: FormControl;
  private _passwordCtrl: FormControl;
  private _confirmeCtrl: FormControl;
  private _formGroup: FormGroup;
  private _erreur: boolean = false;

  constructor(
    private userService: UserService,
    private inscriptionService: InscriptionService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.show1 = false;
    this.show2 = false;

    this.loginCtrl = this.fb.control('', Validators.required, (control) =>
      this.checkUsername(control)
    );
    this.passwordCtrl = this.fb.control('', [
      Validators.required,
      Validators.minLength(4),
    ]);
    this.confirmeCtrl = this.fb.control('', [Validators.required]);
    this.formGroup = this.fb.group(
      {
        login: this.loginCtrl,
        password: this.passwordCtrl,
        confirme: this.confirmeCtrl,
      },
      { validator: InscriptionComponent.MustMatch }
    );
  }

  ngOnInit(): void {}

  inscription() {
    this.inscriptionService.inscription(this.user).subscribe(
      (res) => {
        this.router.navigate(['/login']);
      },
      (erreur) => {
        console.log(erreur);
        this.erreur = true;
      }
    );
  }

  password1() {
    this.show1 = !this.show1;
  }

  password2() {
    this.show2 = !this.show2;
  }

  public checkUsername(control: AbstractControl) {
    return this.userService.checkUsername(control.value).pipe(
      map((res) => {
        if (res) {
          return null;
        } else {
          return { nomIndisponible: true };
        }
      })
    );
  }

  static MustMatch(formGroup: FormGroup) {
    const password = formGroup.controls['password'];
    const confirm = formGroup.controls['confirme'];

    if (password.errors && !confirm.errors) {
      return null;
    }

    if (password.value !== confirm.value) {
      return { mustMatch: true };
    } else {
      return null;
    }
  }

  get user(): Utilisateur {
    return this._user;
  }

  set user(value: Utilisateur) {
    this._user = value;
  }

  get loginCtrl(): FormControl {
    return this._loginCtrl;
  }

  set loginCtrl(value: FormControl) {
    this._loginCtrl = value;
  }

  get passwordCtrl(): FormControl {
    return this._passwordCtrl;
  }

  set passwordCtrl(value: FormControl) {
    this._passwordCtrl = value;
  }

  get confirmeCtrl(): FormControl {
    return this._confirmeCtrl;
  }

  set confirmeCtrl(value: FormControl) {
    this._confirmeCtrl = value;
  }

  get formGroup(): FormGroup {
    return this._formGroup;
  }

  set formGroup(value: FormGroup) {
    this._formGroup = value;
  }

  get erreur(): boolean {
    return this._erreur;
  }

  set erreur(value: boolean) {
    this._erreur = value;
  }
}
