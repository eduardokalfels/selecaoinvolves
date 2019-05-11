import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertaCardListComponent } from './alerta-card-list.component';

describe('AlertaCardListComponent', () => {
  let component: AlertaCardListComponent;
  let fixture: ComponentFixture<AlertaCardListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlertaCardListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlertaCardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
