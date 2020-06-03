import { Component, OnInit } from '@angular/core';
import { RecetteServiceService } from '../../services/recette-service.service';
import { Recette } from '../../model/recette';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent implements OnInit {
  private _recettes: Recette[];
  constructor(private recetteService: RecetteServiceService) {}

  ngOnInit() {
    this.initRecettes();
  }
  private initRecettes() {
    this.recetteService.findAll().subscribe((res) => {
      this._recettes = res;
    });
  }

  get recettes(): Recette[] {
    return this._recettes;
  }

  set recettes(value: Recette[]) {
    this._recettes = value;
  }
}
