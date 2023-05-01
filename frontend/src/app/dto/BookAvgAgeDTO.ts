import { Book } from "../model/Book";

export interface BookAvgAgeDTO{
    id: number,
    titleBook: string,
    numberOfPages: number,
    sellingPrice: number,
    descriptionTheme: string,
    publishYear: number,
    avgAgeAuthors: number
}