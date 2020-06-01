import {Routes} from '@angular/router';
import {IndexComponent} from './components/index/index.component';
import {RecipeCardComponent} from './components/recipe-card/recipe-card.component';
import {MilestonesComponent} from './components/milestones/milestones.component';

export const routes: Routes = [
  {path: 'index', component: IndexComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'}
];
