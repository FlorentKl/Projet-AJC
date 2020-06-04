import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recette } from 'src/app/model/recette';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Difficulte } from '../../model/difficulte.enum';
import { Cout } from '../../model/cout.enum';

@Component({
  selector: 'app-liste-recette',
  templateUrl: './liste-recette.component.html',
  styleUrls: ['./liste-recette.component.css'],
})
export class ListeRecetteComponent implements OnInit {
  private _recettes: Recette[];

  private _namelike: string = '';
  private _note: Number = new Number();
  private _type: any = 'recette';
  private _difficulte: any = '';
  private _cout: any = '';
  private _nodifficulte: any = '';
  private _nocout: any = '';

  private _searchUrl = 'http://localhost:8080/web/rest';

  constructor(
    private activatedRoute: ActivatedRoute,
    private httpClient: HttpClient
  ) {}

  ngOnInit() {
    this.initListe();
  }

  private initListe() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params.namelike) {
        this.namelike = params.namelike;
      }
      if (params.note) {
        this.note = params.note;
      }
      if (params.type) {
        this.type = params.type;
      }
      if (params.difficulte) {
        this.difficulte = params.difficulte;
      }
      if (params.cout) {
        this.cout = params.cout;
      }
      if (params.nodifficulte) {
        this.nodifficulte = params.nodifficulte;
      }
      if (params.nocout) {
        this.nocout = params.nocout;
      }
      console.log(this.type);
      const paramsget = new HttpParams()
        .set('namelike', this._namelike)
        .set('note', this._note.toString())
        .set('difficulte', this._difficulte)
        .set('cout', this._cout)
        .set('nodifficulte', this._nodifficulte)
        .set('nocout', this._nocout);

      this.httpClient
        .get(`${this._searchUrl}/${this.type}/search`, { params: paramsget })
        .subscribe(
          (res) => {
            console.log(res);
            this.recettes = res as Recette[];
            for (const recette of this.recettes) {
              recette.difficulte =
                'Difficulte : ' + Difficulte[recette.difficulte];
              recette.cout = 'Cout : ' + Cout[recette.cout];
            }
          },
          (err) => {
            console.log(err);
          }
        );
    });
  }

  public get recettes(): Recette[] {
    return this._recettes;
  }

  public set recettes(v: Recette[]) {
    this._recettes = v;
  }

  get namelike(): string {
    return this._namelike;
  }

  set namelike(value: string) {
    this._namelike = value;
  }

  get note(): Number {
    return this._note;
  }

  set note(value: Number) {
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

  get searchUrl(): string {
    return this._searchUrl;
  }

  set searchUrl(value: string) {
    this._searchUrl = value;
  }
}
