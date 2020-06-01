import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageRecetteComponent } from './page-recette.component';

describe('PageRecetteComponent', () => {
  let component: PageRecetteComponent;
  let fixture: ComponentFixture<PageRecetteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageRecetteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageRecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
