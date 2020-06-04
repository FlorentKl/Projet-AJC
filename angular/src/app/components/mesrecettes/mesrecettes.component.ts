import { Component, OnInit } from '@angular/core';
import {Difficulte} from '../../model/difficulte.enum';
import {Cout} from '../../model/cout.enum';
import {Recette} from '../../model/recette';
import {RecetteService} from '../../services/recette.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-mesrecettes',
  templateUrl: './mesrecettes.component.html',
  styleUrls: ['./mesrecettes.component.css']
})
export class MesrecettesComponent implements OnInit {
  private _recettes: Recette[];
  private _login: string;


  constructor(private recetteService: RecetteService, private router: Router,) { }

  ngOnInit() {
    this.initRecettes();
    this.login = sessionStorage.getItem('login');
  }

  private initRecettes() {
    this.recetteService.findAll().subscribe((res) => {
      this._recettes = res;
      for (let recette of this._recettes) {
        recette.difficulte = 'Difficulte : ' + Difficulte[recette.difficulte];
        recette.cout = 'Cout : ' + Cout[recette.cout];
      }
    });
  }

  public supprimer(id: number) {
    this.recetteService.delete(id).subscribe(res => {
      this.ngOnInit();
    });
  }

  get recettes(): Recette[] {
    return this._recettes;
  }

  set recettes(value: Recette[]) {
    this._recettes = value;
  }


  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }
}
