import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandableGraphComponent } from './expandable-graph.component';

describe('ExpandableGraphComponent', () => {
  let component: ExpandableGraphComponent;
  let fixture: ComponentFixture<ExpandableGraphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpandableGraphComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpandableGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
