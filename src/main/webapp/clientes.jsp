<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="utf-8">
        <title>Jupiter - Cliente</title>
        <link rel="stylesheet" type="text/css" href="_css/estilo.css">
    </head>
    <body>
        <header>
            <a href="home.jsp"><p id="txtJupiter">JUPITER</p></a>
            <p id="usuario">Bem vindo, ${usuario.nome}</p>
        </header>
        <div id="menuContainer">
            <nav>
                <h2>Menu</h2>
                <ul id="menu">	
                    <a href="${pageContext.request.contextPath}/ListagemClientes"><li id="listaMenu"><img src="_imagens/cliente.png">Clientes</li></a>
                    <li id="listaSubMenu">
                        <img src="_imagens/produto.png">Produtos
                        <ul id="subMenuProdutos">
                            <a href="${pageContext.request.contextPath}/ListagemGames"><li id="itemSub"><img src="_imagens/games.png">Games</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemConsoles"><li id="itemSub"><img src="_imagens/console.png">Consoles</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemAcessorios"><li id="itemSub"><img src="_imagens/acessorio.png">Acessórios</li></a>
                            <a href="${pageContext.request.contextPath}/ListagemActionFigures"><li id="itemSub"><img src="_imagens/actionFigure.png">Action Figures</li></a>
                        </ul>
                    </li>
                    <a href="${pageContext.request.contextPath}/ListagemUsuarios"><li id="listaMenu"><img src="_imagens/funcionario.png">Usuários</li></a>
                    <a href="${pageContext.request.contextPath}/ListagemFiliais"><li id="listaMenu"><img src="_imagens/filial.png">Filiais</li></a>
                    <a href="Vendas_ProcurarCliente.jsp"><li id="listaMenu"><img src="_imagens/venda.png">Vendas</li></a>
                    <a href="${pageContext.request.contextPath}/GerarRelatorios.jsp"><li id="listaMenu"><img src="_imagens/relatorio.png">Relatório</li></a>
                </ul>
            </nav>		
        </div>
        <div id="corpo">
            <a href="${pageContext.request.contextPath}/CadastrarCliente.jsp"><img class="add" src="_imagens/add.png"></a>
            <table class="tabelaResultados">
                <tr>
                    <td><strong>Nome</strong></td>
                    <td><strong>CPF</strong></td>
                    <td><strong>Sexo</strong></td>
                    <td><strong>Data Nascimento</strong></td>
                    <td><strong>Estado Civil Compra</strong></td>
                    <td><strong>Endereço</strong></td>
                    <td><strong>Complemento</strong></td>
                    <td><strong>Número</strong></td>
                    <td><strong>Bairro</strong></td>
                    <td><strong>CEP</strong></td>
                    <td><strong>Cidade</strong></td>
                    <td><strong>Estado</strong></td>
                    <td><strong>Ações</strong></td>
                </tr>
                <c:forEach items="${Listagem}" var="cliente">
                    <tr>
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.sexo}</td>
                        <td>${cliente.dtNascimento}</td>
                        <td>${cliente.estadoCivil}</td>
                        <td>${cliente.endereco}</td>
                        <td>${cliente.complemento}</td>
                        <td>${cliente.numero}</td>
                        <td>${cliente.bairro}</td>
                        <td>${cliente.cep}</td>
                        <td>${cliente.cidade}</td>
                        <td>${cliente.estado}</td>
                        <td>
                            <a href="EditarCliente?id=${cliente.id}">Editar</a> 
                            <a href="ExcluirCliente?id=${cliente.id}">Deletar</a> 
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
        </div>

    </body>
</html>
