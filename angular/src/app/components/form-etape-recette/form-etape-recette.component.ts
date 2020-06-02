import { Component, OnInit } from '@angular/core';
import {EtapeRecette} from '../../model/etape-recette';

@Component({
  selector: 'app-form-etape-recette',
  templateUrl: './form-etape-recette.component.html',
  styleUrls: ['./form-etape-recette.component.css']
})
export class FormEtapeRecetteComponent implements OnInit {

  private _nbEtapes: number = 0;
  private _etapes: Array<number> = new Array();
  private _etapesRecette: Array<EtapeRecette> = new Array<EtapeRecette>();

  constructor() { }

  ngOnInit(): void {
    this._nbEtapes = 0;
    this._etapes = [0];
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));
  }

  public ajouterEtape(){
    this.nbEtapes++;
    this.etapesRecette.push(new EtapeRecette('', this.nbEtapes));
    this.etapes.push(this.nbEtapes);
    console.log(this.etapes);
  }

  public supprimerEtape(){
    if (this.nbEtapes > 0) {
      this.etapesRecette.pop();
      this.nbEtapes--;
      this.etapes.pop();
    }
    console.log(this.etapes);
  }

  public changement(){
    console.log(this.etapesRecette);
  }


  get nbEtapes(): number {
    return this._nbEtapes;
  }

  set nbEtapes(value: number) {
    this._nbEtapes = value;
  }

  get etapes(): Array<number> {
    return this._etapes;
  }

  set etapes(value: Array<number>) {
    this._etapes = value;
  }


  get etapesRecette(): Array<EtapeRecette> {
    return this._etapesRecette;
  }

  set etapesRecette(value: Array<EtapeRecette>) {
    this._etapesRecette = value;
  }
}
