import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import { Song } from "./song/Song";
import {getSongs} from "./utils/Requests";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            songs: [],
            value:''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    display = (value) => {
        let songsList = null;

        getSongs(value)
            .then(response => {
                songsList = response;
            })
            .finally(() => {
                this.setState({
                    songs: songsList
                })
            })
    };

    handleChange(event) {
        this.setState({value: event.target.value});  }
    handleSubmit(event) {
        this.display(this.state.value)
    }




    render() {
        var songs = this.state.songs.map(function (c, index) {
            return (
                <Song postId={c.id} artist={c.artist} song={c.song}
                      link={c.link} text={c.text}/>
            );
        });

        return (
            <div className="centered">

                <input type="text" value={this.state.value} onChange={this.handleChange} />
                <button onClick={this.display}>Sprawdź</button>
                <h2>Piosenki: </h2>
                {songs}
            </div>
        )
    }
}
export default App;
