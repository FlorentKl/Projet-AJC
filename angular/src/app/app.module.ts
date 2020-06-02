import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routes } from './routes';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonsComponent } from './components/buttons/buttons.component';
import { MilestonesComponent } from './components/milestones/milestones.component';
import { RecipeCardComponent } from './components/recipe-card/recipe-card.component';
import { MontestComponent } from './components/montest/montest.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { HomeComponent } from './components/home/home.component';
import { IndexComponent } from './components/index/index.component';
import { FormRecetteComponent } from './components/form-recette/form-recette.component';
import { FormEtapeRecetteComponent } from './components/form-etape-recette/form-etape-recette.component';
import { FormIngredientRecetteComponent } from './components/form-ingredient-recette/form-ingredient-recette.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ButtonsComponent,
    MilestonesComponent,
    RecipeCardComponent,
    MontestComponent,
    RecipeComponent,
    HomeComponent,
    IndexComponent,
    FormRecetteComponent,
    FormEtapeRecetteComponent,
    FormIngredientRecetteComponent
  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(routes), HttpClientModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
