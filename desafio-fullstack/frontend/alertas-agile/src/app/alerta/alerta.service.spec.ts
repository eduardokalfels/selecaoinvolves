import { TestBed } from '@angular/core/testing';

import { AlertaService } from './alerta.service';

describe('AlertasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AlertaService = TestBed.get(AlertaService);
    expect(service).toBeTruthy();
  });
});
