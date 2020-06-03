import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { routeUser } from 'src/app/user/routes';
import { LoginComponent } from './component/login/login.component';
import { InscriptionComponent } from './component/inscription/inscription.component';
import { UserService } from 'src/app/user/services/user.service';

@NgModule({
  declarations: [InscriptionComponent, LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routeUser),
    HttpClientModule,
  ],
  providers: [UserService],
  exports: [InscriptionComponent, LoginComponent],
})
export class UserModule {}
