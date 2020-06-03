import { Routes } from '@angular/router';
import { InscriptionComponent } from 'src/app/user/component/inscription/inscription.component';
import { LoginComponent } from 'src/app/user/component/login/login.component';

export const routeUser: Routes = [
  { path: 'inscription', component: InscriptionComponent },
  { path: 'login', component: LoginComponent },
];
