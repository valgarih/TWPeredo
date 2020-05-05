const {
  Button,
  Icon,
  Typography,
  Paper,
  withStyles,
  Grid,
  ButtonBase,
  Checkbox,
  BottomNavigation,
  BottomNavigationAction
} = window['material-ui'];

const styles = theme => ({
    
  root: {
    textAlign: 'left',
    backgroundColor: '#FE6B8A',
    padding: theme.spacing.unit,
    paddingTop: theme.spacing.unit*6,
    paddingBottom: theme.spacing.unit *39,
    height: '100%',
    overflowX: 'auto'
  },
  icon: {
    margin: theme.spacing.unit,
    fontSize: 32,
  },
  image: {
    position: 'relative',
    maxHeight: 200,
    maxWidth: 500
  },
  main: {
    width: 'auto',
    display: 'block', // Fix IE 11 issue.
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
      width: 400,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  paper: {
    padding: theme.spacing.unit * 20,
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 4,
    

    margin: 'center',
  //  minWidth: 50,
    height: '100%',
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },
  casilla: {
    padding: theme.spacing.unit * 1,
    margin: 'center',
    minWidth: 50,
    backgroundColor: '#e1bee7',
    overflowX: 'auto'
  },
  button: {
    margin: theme.spacing.unit,
  },
});
class Index extends React.Component {
    
    constructor(props){
      super(props);
      this.state = {
          check:[],
          id: "",
          pregunta: "",
          respuestas:[],
          multimedia: "",
          tipo: "",
          anterior: "",
          siguiente: "",
          aciertos: 0,
          errores: 0,
          correctas: [],
          incorrec: 0,
          correc: 0
      }
    }
    
    tipodeMultimedia(tipo){
        const { classes } = this.props;
        console.log("Se llamó a la función, tipo es: "+tipo);
        if(tipo==="jpg" || tipo==="png"){
                return (<ButtonBase className={classes.image}> <img className={classes.img} src={this.state.multimedia} /> </ButtonBase>);
            }else if(tipo==="mp4"){
                return (<video controls> <source src={this.state.multimedia} type='video/mp4' /> </video>);
            }else if(tipo==="mp3"){
                return (<audio controls> <source src={this.state.multimedia} type='audio/mpeg' /> </audio>);
            }
    }
    
    handleChange = name => event => {
    this.setState({ [name]: event.target.checked });
    }
    
    handleToggle = value => () => {
    const { check } = this.state;
    const currentIndex = check.indexOf(value);
    const newChecked = [...check];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    this.setState({
      check: newChecked,
    });
    }
    
    
    componentDidMount = () => {
      fetch("pregunta.json", {
          method: "GET"
      }).then(response => response.json())
        .then( json =>{
            console.log(json);
            var x = json.multimedia.substring(json.multimedia.length -3, json.multimedia.length);
            console.log(x);
            this.setState ({
                id: json.id,
                pregunta: json.pregunta,
                respuestas: json.resp,
                multimedia: json.multimedia,
                anterior: json.anterior,
                siguiente: json.siguiente,
                examen: json.examen,
                correctas: json.corr,
                tipo: x
      });return x;});
  }
  
  render() {
    const {classes} = this.props;
    return (
     <div> 
      <Paper className={classes.root}> 
       <Paper className={classes.paper}>   
                <Typography gutterBottom variant="h6">
                  {this.state.id}
                </Typography>
                <Typography variant="h5">{this.state.pregunta}</Typography>


                <Grid container item xs={24} spacing={24}>
                    <Grid item xs={8}>
                      <Paper className={classes.casilla}>
                        <Grid container direction="row" alignItems="center">
                            <Grid item>
                                <Checkbox color="#FFF" />
                            </Grid>
                            <Grid item>
                                <Typography variant="body1">Verdadero</Typography>
                            </Grid>
                        </Grid>
                      </Paper>
                    </Grid>
                  </Grid>
               
                  
                  <Grid container item xs={24} spacing={24}>
                    <Grid item xs={8}>
                      <Paper className={classes.casilla}>
                        <Grid container direction="row" alignItems="center">
                            <Grid item>
                                <Checkbox color="#FFF" />
                            </Grid>
                            <Grid item>
                                <Typography variant="body1">Falso</Typography>
                            </Grid>
                        </Grid>
                      </Paper>
                    </Grid>
                  </Grid>
                  <br /><br />  


          <Grid item justify="center">
              {this.tipodeMultimedia(this.state.tipo)}
          </Grid>
          
         <BottomNavigation
          onChange={this.handleChange}
          showLabels
          className={classes.paper}
        >
          <BottomNavigationAction label={'Anterior: "'+this.state.anterior+'"'}  onClick = {() => window.location.replace("verE?examen="+this.state.examen+"&pregunta="+this.state.anterior)} icon={ <Icon className={classes.icon}>reply</Icon>  } />
          <BottomNavigationAction label={'Siguiente: "'+this.state.siguiente+'"'} onClick = {() => window.location.replace("verE?examen="+this.state.examen+"&pregunta="+this.state.siguiente)} icon={<Icon className={classes.icon}>redo</Icon>} />
          </BottomNavigation>
        
        <a href="tableExam.html">
                <Button fullWidth variant="contained" color="#FFF">Regresar </Button>
        </a> 
         
       </Paper>

     

                   
       </Paper>

     </div> 
    );
  }
}

const VexamenFV  = withStyles(styles)(Index);
ReactDOM.render(<VexamenFV/>, document.getElementById('root'));

