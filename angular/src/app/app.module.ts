import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routes } from './routes';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonsComponent } from './components/buttons/buttons.component';
import { MilestonesComponent } from './components/milestones/milestones.component';
import { RecipeCardComponent } from './components/recipe-card/recipe-card.component';
import { MontestComponent } from './components/montest/montest.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ButtonsComponent,
    MilestonesComponent,
    RecipeCardComponent,
<<<<<<< HEAD
    MontestComponent,
    RecipeComponent
  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(routes), HttpClientModule
=======
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
>>>>>>> master
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
