import { Component, OnInit } from '@angular/core';
import { Recette } from '../../model/recette';
import { Difficulte } from '../../model/difficulte.enum';
import { RecetteService } from '../../services/recette.service';
import { Cout } from '../../model/cout.enum';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent implements OnInit {
  private _recettes: Recette[];
  private _bestRecettes;

  private _recettesBis: Recette[];

  constructor(private recetteService: RecetteService) {}

  ngOnInit() {
    this.initRecettes();
    this.initRecettesFaciles();
  }
  private initRecettes() {
    this.recetteService.findAll().subscribe((res) => {
      this.recettes = res;
      for (let recette of this.recettes) {
        recette.difficulte = 'Difficulte : ' + Difficulte[recette.difficulte];
        recette.cout = 'Cout : ' + Cout[recette.cout];
      }
    });
    this.recetteService.findBest().subscribe((res) => {
      this.bestRecettes = res;
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

  public get bestRecettes(): Recette[] {
    return this._bestRecettes;
  }

  public set bestRecettes(v: Recette[]) {
    this._bestRecettes = v;
  }
}
