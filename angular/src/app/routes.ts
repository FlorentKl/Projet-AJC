import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { RecipeCardComponent } from './components/recipe-card/recipe-card.component';
import { FormRecetteComponent } from './components/form-recette/form-recette.component';
import { FormIngredientRecetteComponent } from './components/form-ingredient-recette/form-ingredient-recette.component';
import { FormEtapeRecetteComponent } from './components/form-etape-recette/form-etape-recette.component';
import { BoissonComponent } from './components/boisson/boisson.component';
import { EntreeComponent } from './components/entree/entree.component';
import { PlatComponent } from './components/plat/plat.component';
import { DessertComponent } from './components/dessert/dessert.component';
import { RechercheComponent } from './components/recherche/recherche.component';
import { ListeRecetteComponent } from 'src/app/components/liste-recette/liste-recette.component';

export const routes: Routes = [
  { path: 'index', component: IndexComponent },
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'recettes/:id', component: RecipeCardComponent },
  { path: 'boissons', component: BoissonComponent },
  { path: 'entrees', component: EntreeComponent },
  { path: 'plats', component: PlatComponent },
  { path: 'desserts', component: DessertComponent },
  { path: 'recette/edit', component: FormRecetteComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'liste', component: ListeRecetteComponent },
];
