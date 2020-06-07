import React, {Component} from "react";
import './Song.css'
import styles from './Song.css';

export class Song extends Component {
    constructor(props) {
        super(props);
        this.state = {
            songId: props.songId,
            artist: props.artist,
            song: props.song,
            link: props.link,
            text: props.text
        }
    }

    render() {
        const songId = this.state.songId;
        const artist = this.state.artist;
        const song = this.state.song;
        const link = this.state.link;
        const text = this.state.text;


        // return <article className="Post" ref="Post">
        //     <header>
        //         <div>
        //             <div>
        //                 <h1>
        //                     {artist}
        //                 </h1>
        //                <h1>
        //                    {song}
        //                </h1>
        //
        //             </div>
        //         </div>
        //     </header>
        //     <div>
        //         <div>
        //             <h1>
        //                 {link}
        //             </h1>
        //
        //         </div>
        //     </div>
        //     <div className="Post-description">
        //         <label>{text}</label>
        //     </div>
        // </article>;

        return (
            // <div className={styles.app}>
            //
            //     <table className={styles.table}>
            //         <thead>
            //         <tr>
            //             <th>Artysta</th>
            //             <th>Piosenka</th>
            //             <th>Tekst</th>
            //         </tr>
            //         </thead>
            //         <tbody>
            //         <tr>
            //             <td>{artist}</td>
            //             <td>{song}</td>
            //             <td>{text}</td>
            //         </tr>
            //
            //         </tbody>
            //     </table>
            //
            //
            //
            //
            // </div>
            <ul className='list-group mb-4'>
               <li>{artist} {song} {text} </li>
            </ul>
        );
    }

}