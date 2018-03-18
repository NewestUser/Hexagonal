# Example of DI and Hexagonal Architecture


#### The project attempts to ilustrate how a hexagonal architecture can be implemented.


The project consists of 4 modules:
- `core.professionals`
- `gateway`
- `persistence`
- `web`

The core functionality or the business logic is implemented in `core.professionals`.
That module does not have a dependency to the the other 3 modules.  

The rest of the modules represent details that are not essential to the business logic.
Note that those details are <b>package private</b> and are visible only in the module where
they are defined. In this way they remain hidden from the `core.professionals` module which 
is what is essential in our application. 

Those details are: 
- `gateway` - a module that implements logic for contacting external to the system payment providers.
- `persistence` - a module that implements specific tooling like storage mechanisms.
- `web` - a module that represents a transportation mechanism or in this case http.
