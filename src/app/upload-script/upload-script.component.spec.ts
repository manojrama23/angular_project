import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadScriptComponent } from './upload-script.component';

describe('UploadScriptComponent', () => {
  let component: UploadScriptComponent;
  let fixture: ComponentFixture<UploadScriptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadScriptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadScriptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
