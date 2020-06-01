import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RecetteServiceService} from '../../services/recette-service.service';
import {Recette} from '../../model/recette';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.css']
})
export class RecipeCardComponent implements OnInit {
  private _recette: Recette = new Recette();
  private _erreur: boolean = false;
  private _id: number;
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private recetteService: RecetteServiceService ) { }

  ngOnInit(){
    this._erreur = false;
    this.activatedRoute.params.subscribe(params => {
      if (params.id){
        this._id = params.id;
        this.recetteService.findById(this._id).subscribe(
          res => {
            this._recette = res;
          }
        );
      }
    });
  }

  get recette(): Recette {
    return this._recette;
  }

  set recette(value: Recette) {
    this._recette = value;
  }

  get erreur(): boolean {
    return this._erreur;
  }

  set erreur(value: boolean) {
    this._erreur = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }
}
