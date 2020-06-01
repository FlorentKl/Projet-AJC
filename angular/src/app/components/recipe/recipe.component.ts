import { Component, OnInit } from '@angular/core';
import {Recette} from '../../model/recette';
import {RecetteService} from '../../services/recette.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  private _recette: Recette = new Recette();
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
        });
      }
    });
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
