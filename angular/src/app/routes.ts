import {Routes} from '@angular/router';
import {MilestonesComponent} from './components/milestones/milestones.component';
import {MontestComponent} from './components/montest/montest.component';
import {RecipeComponent} from './components/recipe/recipe.component';

export const routes: Routes = [
  {path: 'produits', component: RecipeComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];
