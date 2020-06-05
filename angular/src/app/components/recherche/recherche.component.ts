import { Component, OnInit } from '@angular/core';
import {RecetteService} from '../../services/recette.service';
import {Recette} from '../../model/recette';
import {Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {
  private _namelike: string = '';
  private _note: number;
  private _type: any = '';
  private _difficulte: any = '';
  private _cout: any='';
  private _nodifficulte: any = '';
  private _nocout: any = '';
  searchUrl = 'http://localhost:8080/web/rest/recette/search';

  constructor(private router: Router) { }

  ngOnInit() {
  }
  public rechercher(){
    console.log(this.difficulte);
  this.router.navigate(['/liste'],
    {queryParams: {
        namelike: this.namelike,
        note: this.note,
        type: this.type,
        difficulte: this.difficulte,
        cout: this.cout,
        nodiff: this.nodifficulte,
        nocout: this.nocout
      }});
  }

  get namelike(): string {
    return this._namelike;
  }

  set namelike(value: string) {
    this._namelike = value;
  }

  get note(): number {
    return this._note;
  }

  set note(value: number) {
    this._note = value;
  }

  get type(): any {
    return this._type;
  }

  set type(value: any) {
    this._type = value;
  }

  get difficulte(): any {
    return this._difficulte;
  }

  set difficulte(value: any) {
    this._difficulte = value;
  }

  get cout(): any {
    return this._cout;
  }

  set cout(value: any) {
    this._cout = value;
  }

  get nodifficulte(): any {
    return this._nodifficulte;
  }

  set nodifficulte(value: any) {
    this._nodifficulte = value;
  }

  get nocout(): any {
    return this._nocout;
  }

  set nocout(value: any) {
    this._nocout = value;
  }
}
