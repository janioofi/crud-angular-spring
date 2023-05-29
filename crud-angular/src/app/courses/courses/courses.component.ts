import { CoursesService } from './../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

	courses$: Observable<Course[]>;
	displayedColumns = [ 'name', 'category'];

	//courseService :CoursesService;

	constructor(private courseService :CoursesService){
		//this.courses = [];
		//this.courseService = new CoursesService();
		this.courses$ = this.courseService.list();
	}

	ngOnInit(): void {

	}
}
