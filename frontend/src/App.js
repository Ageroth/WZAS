import React, {Component} from 'react';
import './App.css';
import {Song} from "./song/Song";
import {getSongsByText} from "./utils/Requests";


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            songs: [],
            text: '',
            isChecked: true
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({text: event.target.value});
    }

    handleSubmit() {
        this.setState({
            songs: [],
        });
        const flag = Object.assign({}, {flag: this.state.isChecked});

        let songsList = null;

        getSongsByText(this.state.text, flag)
            .then(response => {
                songsList = response
            })
            .finally(() => {
                this.setState({
                    songs: songsList,
                    text: '',
                })
            })
    }

    toggleChange = () => {
        this.setState({
            isChecked: !this.state.isChecked,
        });
    }

    render() {
        const result = this.state.songs.map(function (c) {
            return (
                <Song postId={c.id} artist={c.artist} song={c.song}
                      word={c.word} text={c.text}/>
            );
        });

        return (
            <div className="centered">
                <h1>Piosenki: </h1>
                <textarea value={this.state.text} onChange={this.handleChange}/>

                <div>
                    <label>
                        <input type="checkbox"
                               checked={this.state.isChecked}
                               onChange={this.toggleChange}
                        />
                        Pomijaj popularne słowa
                    </label>
                </div>
                <div>
                    <button onClick={this.handleSubmit}>Sprawdź</button>
                </div>
                {result}
            </div>
        )
    }
}

export default App;
