import { Component, OnInit } from '@angular/core';
import {RecetteService} from '../../services/recette.service';
import {Recette} from '../../model/recette';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {
  private _recette: Recette = new Recette();

  constructor(private recetteService: RecetteService) { }

  ngOnInit() {
  }


  get recette(): Recette {
    return this._recette;
  }

  set recette(value: Recette) {
    this._recette = value;
  }
}
