import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  public get login() {
    return sessionStorage.getItem('login');
  }

  public logout() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('login');
    this.router.navigate(['/index']);
  }

  public recherche(event: any) {
    this.router.navigate(['/liste'], {
      queryParams: { namelike: event.target.value },
    });
  }
}
