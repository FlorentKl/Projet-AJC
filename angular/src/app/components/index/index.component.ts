import { Component, OnInit } from '@angular/core';
import { Recette } from '../../model/recette';
import { Difficulte } from '../../model/difficulte.enum';
import { RecetteService } from '../../services/recette.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent implements OnInit {
  private _recettes: Recette[];

  private _recettesBis: Recette[];

  constructor(private recetteService: RecetteService) {}

  ngOnInit() {
    this.initRecettes();
    this.initRecettesFaciles();
  }
  private initRecettes() {
    this.recetteService.findAll().subscribe((res) => {
      this._recettes = res;
    });
  }

  private initRecettesFaciles() {
    this.recetteService
      .findByNomDiffAndCout(
        undefined,
        Difficulte[Difficulte.F],
        undefined,
        undefined,
        undefined
      )
      .subscribe((res) => {
        console.log(res);
      });
  }
  get recettes(): Recette[] {
    return this._recettes;
  }

  set recettes(value: Recette[]) {
    this._recettes = value;
  }

  get recettesBis(): Recette[] {
    return this._recettesBis;
  }

  set recettesBis(value: Recette[]) {
    this._recettesBis = value;
  }
}
