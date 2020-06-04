import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recette } from 'src/app/model/recette';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Difficulte} from '../../model/difficulte.enum';
import {Cout} from '../../model/cout.enum';

@Component({
  selector: 'app-liste-recette',
  templateUrl: './liste-recette.component.html',
  styleUrls: ['./liste-recette.component.css'],
})
export class ListeRecetteComponent implements OnInit {
  private _recettes: Recette[];

  namelike: string;
  searchUrl = 'http://localhost:8080/web/rest/recette/search';

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
      let truc = new HttpParams().set('namelike', this.namelike);

      this.httpClient.get(this.searchUrl, { params: truc }).subscribe(
        (res) => {
          console.log(res);
          this.recettes = res as Recette[];
          for (let recette of this.recettes) {
            recette.difficulte = 'Difficulte : ' + Difficulte[recette.difficulte];
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
}
