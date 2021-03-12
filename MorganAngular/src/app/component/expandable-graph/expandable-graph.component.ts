import { Component, Input, OnInit } from '@angular/core';

export interface graphData{
  name : string,
  type : string,
  graphx : [],
  graphy : []
}

@Component({
  selector: 'app-expandable-graph',
  templateUrl: './expandable-graph.component.html',
  styleUrls: ['./expandable-graph.component.css']
})
export class ExpandableGraphComponent implements OnInit {
  
  @Input() graphData: graphData;
  graph = {
    data: [{ x: [] , y : [], values : [], labels : [] ,type : ""  }],
    layout: {width: 500, height: 500 ,title: '', showlegend: false}
};

  
  constructor() { }

  ngOnInit(): void {
 
  console.log(this.graph)
  }

  loadGraph(){
    let xlist = this.graphData.graphx;
    let ylist = [];
  this.graphData.graphy.forEach(ele => {
    ylist.push(ele)
  })
  
  this.graph = {
    data: [{ x: xlist , y : ylist, values: ylist, labels: xlist ,type : this.graphData.type  }],
    layout: {width: 500, height: 500 ,title: this.graphData.name, showlegend: false}
    };
  }

}
