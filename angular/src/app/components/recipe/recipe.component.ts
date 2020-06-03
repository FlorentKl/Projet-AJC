import { Component, OnInit } from '@angular/core';
import {Recette} from '../../model/recette';
import {RecetteService} from '../../services/recette.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Cout} from '../../model/cout.enum';
import {Difficulte} from '../../model/difficulte.enum';
import {Unite} from '../../model/unite.enum';
import {EtapeRecette} from '../../model/etape-recette';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  private _recette: Recette = new Recette();
  private _coutString: string;
  private _difficulteString: string;
  private _id: number;
  private _erreur: boolean = false;

  constructor(private activatedRoute: ActivatedRoute, private recetteService: RecetteService, private router: Router) { }

  ngOnInit(): void {
    this.initRecette();
  }

  private initRecette(){
    this.erreur = false;
    this.activatedRoute.queryParams.subscribe(params => {
      if (params.id) {
        this._id = params.id;
        this.recetteService.findById(this._id).subscribe(data => {
          this._recette = data;
          this._coutString = Cout[data.cout];
          this.difficulteString = Difficulte[data.difficulte];
          for (let ing of data.ingredients) {
            ing.unite = Unite[ing.unite];
          }
          data.etapes.sort(function(a, b){
            return a.numEtape - b.numEtape;
          });

        });
      }
    });
  }

  get difficulteString(): string {
    return this._difficulteString;
  }

  set difficulteString(value: string) {
    this._difficulteString = value;
  }

  get coutString(): string {
    return this._coutString;
  }

  set coutString(value: string) {
    this._coutString = value;
  }

  get recette(): Recette {
    return this._recette;
  }

  set recette(value: Recette) {
    this._recette = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get erreur(): boolean {
    return this._erreur;
  }

  set erreur(value: boolean) {
    this._erreur = value;
  }
}
