import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import { List } from "./List";

class App extends Component {

    state = {
        contacts: []
    };

    componentDidMount() {
        fetch( 'http://localhost:8080/songs/Hd0vdnIBEeMqUtusEjob')
            .then(res => res.json())
            .then(json => this.setState({ contacts: json.results }));
    }





    render() {

        return (
            <div className="App">
                <header className="App-header">
                    Witamy
                    <input onInput={}/>
                    <button>
                        Nacisnij
                    </button>
                        <List contacts={this.state.contacts} />
                </header>

            </div>
        );
    }
}
export default App;
