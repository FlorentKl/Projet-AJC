import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routes } from './routes';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { BoissonComponent } from './components/boisson/boisson.component';
import { EntreeComponent } from './components/entree/entree.component';
import { PlatComponent } from './components/plat/plat.component';
import { DessertComponent } from './components/dessert/dessert.component';
import { UserModule } from 'src/app/user/user.module';
import { RechercheComponent } from './components/recherche/recherche.component';
import { ListeRecetteComponent } from './components/liste-recette/liste-recette.component';

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
    FormIngredientRecetteComponent,
    BoissonComponent,
    EntreeComponent,
    PlatComponent,
    DessertComponent,
    RechercheComponent,
    ListeRecetteComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    ReactiveFormsModule,
    UserModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
