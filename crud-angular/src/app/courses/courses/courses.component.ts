import { Component } from '@angular/core';
import { Course } from '../model/course'

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {

	courses :  Course[]  = [
		{_id:'1', name:'Angular', category:'Programação'},
		{_id:'2', name:'JavaScript', category:'Programação'},
		{_id:'3', name:'Java', category:'Programação'},
		{_id:'4', name:'Excel', category:'Informática'},
	];
	displayedColumns = [ 'name', 'category'];

	constructor(){
		//this.courses = [];
	}
}
