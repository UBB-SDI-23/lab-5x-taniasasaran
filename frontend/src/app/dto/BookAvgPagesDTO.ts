import { Book } from "../model/Book";

export interface BookAvgPagesDTO{
    bookDTO: Book,
    avgPages: number
}