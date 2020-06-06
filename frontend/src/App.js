import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import { Song } from "./song/Song";
import {getSongs} from "./utils/Requests";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            songs: []
        };
    }


    componentDidMount = () => {
        let songsList = null;

        getSongs()
            .then(response => {
                songsList = response;
            })
            .finally(() => {
                this.setState({
                    songs: songsList
                })
            })
    };





    render() {
        var songs = this.state.songs.map(function (c, index) {
            return (
                <Song postId={c.id} artist={c.artist} song={c.song}
                      link={c.link} text={c.text}/>
            );
        });

        return (
            <div className="centered">
                <h2>Piosenki: </h2>
                {songs}
            </div>
        )
    }
}
export default App;
