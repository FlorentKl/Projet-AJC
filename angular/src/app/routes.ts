import {Routes} from '@angular/router';
import {IndexComponent} from './components/index/index.component';
import {RecipeComponent} from './components/recipe/recipe.component';
import {RecipeCardComponent} from './components/recipe-card/recipe-card.component';
import {FormRecetteComponent} from './components/form-recette/form-recette.component';
import {FormIngredientRecetteComponent} from './components/form-ingredient-recette/form-ingredient-recette.component';
import {FormEtapeRecetteComponent} from './components/form-etape-recette/form-etape-recette.component';

export const routes: Routes = [
  {path: 'index', component: IndexComponent},
  {path: 'recette', component: RecipeComponent},
  {path: 'recette/edit', component: FormRecetteComponent},
  {path: 'recette/edit/ingredients', component: FormIngredientRecetteComponent},
  {path: 'recette/edit/etapes', component: FormEtapeRecetteComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'}

];
