import { CoursesService } from '../../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../../model/course';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
	selector: 'app-courses',
	templateUrl: './courses.component.html',
	styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {
	courses$: Observable<Course[]>;
	readonly displayedColumns = ['name', 'category', 'hours', 'actions'];

	//courseService :CoursesService;

	constructor(
		private courseService: CoursesService,
		public dialog: MatDialog,
		private router: Router,
		private route: ActivatedRoute
	) {
		//this.courses = [];
		//this.courseService = new CoursesService();
		this.courses$ = this.courseService.list().pipe(
			catchError((error) => {
				this.onError("Erro ao carregar cursos");
				return of([]);
			})
		);
	}
	ngOnInit(): void {
	}

	onError(errorMsg: string) {
		this.dialog.open(ErrorDialogComponent, {
			data: errorMsg
		});
	}

	onAdd(){
		this.router.navigate(['new'], {relativeTo: this.route})
	}
}