## Queries:
These queries can be directly pasted in the graphiQL
- **getAuthor:**
    ```shell
    query {
        authors {
            id
            lastName
            firstName
            books {
                isbn
                title
            }
        }
    }

- **getAuthorById:**
    ```shell
    query {
        authorById(id: 0) {
            id
            lastName
            firstName
            books {
                isbn
                title
            }
        }
    }

- **getBooks:**
    ```shell
    query {
        books {
            isbn
            title
            authorId
        }
    }

- **getBooksByISBN:**
    ```shell
    query {
        bookByISBN(isbn: "123456789") {
            isbn
            title
            authorId
        }
    }
  
## Mutations:

Here are the mutations examples:
- **addAuthor:**
    ```shell
    mutation {
        addAuthor(input: {
            firstName: "Subham"
            lastName:"Kumar"
        }) {
            author {
                id
                firstName
                lastName
            }
        }
    }

  
- **addBook:**
     ```shell
     mutation {
         addBook(input: {
           isbn: "2222"
           title: "Test book2"
           authorId: 1
         }) {
             book {
                 isbn
                 title
                 authorId
             }
         }
     }
  