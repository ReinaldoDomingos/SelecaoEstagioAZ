<template>
    <v-app>
        <v-data-table :headers="headers" :items="items" sort-by="id" class="elevation-1"
                      :search="search" no-results-text="Nenhum resultado encontrado"
                      no-data-text="Nenhuma empresa cadstrada">
            <template v-slot:top>
                <v-card-title>
                    Empresas Cadastradas
                    <v-spacer></v-spacer>
                    <v-text-field
                            v-model="search"
                            append-icon="mdi-magnify"
                            label="Pesquisar por empresa"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-card-title>
                <div class="row">
                    <div class="col-12">
                        <v-btn color="primary" dark class="float-right ma-2 ma-lg-3" @click="editItem()">
                            Nova Empresa
                        </v-btn>
                    </div>
                </div>
            </template>
            <template v-slot:item.actions="{ item }">
                <v-icon small class="mr-2" @click="editItem(item.id)">mdi-pencil</v-icon>
                <v-icon small color="" @click="deleteItem(item)">mdi-delete</v-icon>
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="iniciar">
                    <v-icon class="mr-2">mdi-reload</v-icon>
                    Recarregar
                </v-btn>
            </template>
        </v-data-table>
    </v-app>
</template>


<script>
    import axios from "axios";


    const baseURLSerividor = 'http://localhost:8081'
    const baseURL = 'http://localhost:8080'
    export default {
        data: () => ({
            search: '',
            dialog: false,
            headers: [
                {
                    text: 'CNPJ',
                    align: 'start',
                    sortable: false,
                    value: 'cnpj',
                },
                {text: 'Razão Social', value: 'razaoSocial'},
                {text: 'Telefone', value: 'telefone'},
                {text: 'email', value: 'email'},
                {text: 'Ações', value: 'actions'},
            ],
            items: [],
            editedIndex: -1,
            editedItem: {},
            defaultItem: {
                nome: '',
            },
        }),

        computed: {
            formTitle() {
                return this.editedIndex === -1 ? 'Novo Empresa' : 'Editar Empresa'
            },
        },

        watch: {
            dialog(val) {
                val || this.close()
            },
        },
        created() {
            this.iniciar()
        },
        beforeUpdate() {
            this.iniciar()
        },
        methods: {
            iniciar() {
                let api = baseURLSerividor + '/empresas';
                axios.get(api).then(res => {
                    this.items = res.data;
                }).catch(err => {
                    console.log(err.response);
                });
            },
            editItem(id) {
                if (id)
                    location.href = baseURL + '/#/empresa/' + id
                else
                    location.href = baseURL + '/#/empresa/'
            },
            deleteItem(item) {
                const index = this.items.indexOf(item)
                let id = this.items[index].id;
                if (confirm('Tem certeza de que deseja excluir este item?')) {
                    this.items.splice(index, 1)
                    axios.delete(baseURLSerividor + "/empresa/" + id)
                }
            },
            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.items[this.editedIndex], this.editedItem)
                    let empresa = this.items[this.editedIndex];
                    axios.put(baseURLSerividor + "/empresas/" + empresa.id, JSON.stringify(empresa), {
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                            "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                            'Content-Type': 'application/json',
                        },
                    })
                } else {
                    this.items.push(this.editedItem)
                }
                this.close()
            },
        },
    }
</script>

<style>
    .v-data-table theme--light {
        padding: 5% !important;
    }
</style>



